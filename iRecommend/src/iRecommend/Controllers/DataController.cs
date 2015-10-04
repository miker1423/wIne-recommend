using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNet.Mvc;
using asprise_ocr_api;
using Microsoft.AspNet.Hosting;
using Microsoft.AspNet.Http;
using System.Net.Http.Headers;
using System;

using iRecommend.Models;

namespace iRecommend.Controllers
{
	class DataController : Controller
	{
		private AspriseOCR client;
		[FromServices]
		private IPlatoRepository plato { get; set; }
		private readonly IHostingEnvironment _hosting;
		public DataController(IHostingEnvironment ev){
			_hosting=ev;
		}
		
		[HttpPost]
		public async Task<List<string>> getWines(IFormFile file)
		{
			
			
			var fileName=ContentDispositionHeaderValue.Parse(file.ContentDisposition)
													   .FileName
													   .Trim('"');  
			
			var filePath=_hosting.WebRootPath+"\\Documents\\"+DateTime.Now.ToString("yyyyddMHHmmss")+fileName;
			file.SaveAs(filePath);								   
			
			List<string> platos=new List<string>();
			AspriseOCR.SetUp();
			client=new AspriseOCR();
			client.StartEngine("eng",AspriseOCR.SPEED_SLOW);
			string text=client.Recognize(filePath, -1,-1,-1,-1,-1,AspriseOCR.RECOGNIZE_TYPE_ALL, AspriseOCR.OUTPUT_FORMAT_PLAINTEXT);
			
			string[] list=text.Split(new char[] {' '});
			List<string> selected=await plato.LookFor(list);
			
			return selected;
		}
	}
}