// https://cheatsheetseries.owasp.org/cheatsheets/XML_External_Entity_Prevention_Cheat_Sheet.html#net
using Microsoft.AspNetCore.Mvc;
using System.Xml;
using System.Xml.XPath;
using System.Text;

[ApiController]
[Route("test")]
public class Program : ControllerBase
{
    [HttpGet]
    [Route("a")]
    public IActionResult GetA(string q)
    {
        Stream s = new MemoryStream(Encoding.UTF8.GetBytes(q));
        XmlTextReader reader = new XmlTextReader(s);
//      // Fix for < .NET 4.0
//      reader.ProhibitDtd = true;
//      // Fix for >= .NET 4.0
//      reader.DtdProcessing = DtdProcessing.Prohibit;

        while (reader.Read())
        {
            Console.WriteLine(reader.Name);
            Console.WriteLine(reader.Value);
        }
        reader.Close();

        return Ok(new
        {
            Message = "A q: " + q,
        });
    }

    [HttpGet]
    [Route("b")]
    public IActionResult GetB(string q)
    {
        XmlDocument doc = new XmlDocument();
//      // Since .NET Framework 4.5.2 `null` is the default option (no fix needed).
//      doc.XmlResolver = null;
        doc.LoadXml(q);

        var l = doc.GetElementsByTagName("foo");
        Console.WriteLine(l.Count);
        for (int i = 0; i < l.Count; i++)
        {
            Console.WriteLine(l[i].InnerText);
        }

        return Ok(new
        {
            Message = "B q: " + q,
        });
    }

    [HttpGet]
    [Route("c")]
    public IActionResult GetC(string q)
    {

        XPathDocument doc = new XPathDocument(new StringReader(q));
//      // Needed for versions below .NET Framework 4.5.2.
//      XPathDocument doc = new XPathDocument(XmlReader.Create(new StringReader(q)));
        var nav = doc.CreateNavigator();
        var i = nav.Select("//foo");

        while (i.MoveNext())
        {
            Console.WriteLine(i.Current.Value);
        }

        return Ok(new
        {
            Message = "C q: " + q,
        });
    }

    static void Main(string[] args)
    {
        var builder = WebApplication.CreateBuilder(args);
        builder.Services.AddControllers();
        var app = builder.Build();
        app.MapControllers();
        app.Run();
    }
}
