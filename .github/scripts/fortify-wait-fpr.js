const fs = require("node:fs");
const timers = require("node:timers/promises");

const scanId = process.argv[2];
const FORTIFY_USER = process.env.FORTIFY_USER;
const FORTIFY_TOKEN = process.env.FORTIFY_TOKEN;
const FORTIFY_TENANT = process.env.FORTIFY_TENANT;

const countInString = function (haystack, needle) {
  var count = 0;
  var position = 0;
  while (true) {
    position = haystack.indexOf(needle, position);
    if (position != -1) {
      count++;
      position += needle.length;
    } else {
      break;
    }
  }
  return count;
};

async function main() {
  const tokenData = await fetch("https://api.ams.fortify.com/oauth/token", {
    method: "POST",
    headers: { "content-type": "application/x-www-form-urlencoded" },
    body: `grant_type=password&scope=api-tenant&username=${FORTIFY_TENANT}\\${FORTIFY_USER}&password=${FORTIFY_TOKEN}&security_code=`,
  }).then((r) => r.json());

  if (!tokenData || !tokenData.access_token) {
    throw new Error("Can't authenticate, check credentials.");
  }

  const token = tokenData.access_token;

  while (true) {
    const summaryResponse = await fetch(
      `https://api.ams.fortify.com/api/v3/scans/${scanId}/summary`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (summaryResponse.status === 200) {
      const summaryData = await summaryResponse.json();

      if (summaryData.analysisStatusType === "Completed") {
        break;
      }
      console.log(`Scan status: ${summaryData.analysisStatusType}...`);
    } else {
      console.log(`Scan API status: ${summaryResponse.status}...`);
    }
    await timers.setTimeout(5000);
  }

  let buffer;
  let respText;

  while (true) {
    const fileResponse = await fetch(
      `https://api.ams.fortify.com/api/v3/scans/${scanId}/fpr`,
      {
        headers: { Authorization: `Bearer ${token}` },
      }
    );

    if (fileResponse.status === 200) {
      respText = await fileResponse.text();
      buffer = Buffer.from(respText, "utf-8");
      break;
    }

    if ([202, 429].includes(fileResponse.status)) {
      console.log(`Waiting FRP file...`);
      await timers.setTimeout(5000);
    } else {
      throw new Error(
        `Unexpected status code from fpr endpoint: ${fileResponse.status}.`
      );
    }
  }

  fs.writeFileSync("./scandata.fpr", Buffer.from(buffer));
  const numberOfInfoSevIssues = countInString(
    respText,
    "<InstanceSeverity>1.0</InstanceSeverity>"
  );
  const numberOfLowSevIssues = countInString(
    respText,
    "<InstanceSeverity>2.0</InstanceSeverity>"
  );
  const numberOfMediumSevIssues = countInString(
    respText,
    "<InstanceSeverity>3.0</InstanceSeverity>"
  );
  const numberOfHighSevIssues = countInString(
    respText,
    "<InstanceSeverity>4.0</InstanceSeverity>"
  );
  const numberOfCriticalSevIssues = countInString(
    respText,
    "<InstanceSeverity>5.0</InstanceSeverity>"
  );
  const hasBlockingIssues =
    numberOfCriticalSevIssues > 0 || numberOfHighSevIssues > 0;
  console.log(
    `Scan complete, number of info severity issues: ${numberOfInfoSevIssues}, number of low severity issues: ${numberOfLowSevIssues}, number of medium severity issues: ${numberOfMediumSevIssues}, number of high severity issues: ${numberOfHighSevIssues}, number of critical severity issues: ${numberOfCriticalSevIssues}`
  );
  if (hasBlockingIssues) {
    process.exit(1);
  }
}

main().catch(console.error);
