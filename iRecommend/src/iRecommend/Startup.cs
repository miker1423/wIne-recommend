using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNet.Builder;
using Microsoft.AspNet.Hosting;
using Microsoft.AspNet.Http;
using Microsoft.AspNet.Mvc;
using Microsoft.Dnx.Runtime;
using Microsoft.Framework.Configuration;
using Microsoft.Framework.DependencyInjection;

using iRecommend.Models;
using iRecommend.Services;

namespace iRecommend
{
    public class Startup
    {
        public Startup(IHostingEnvironment env, IApplicationEnvironment appEnv)
        {
            var builder=new ConfigurationBuilder(appEnv.ApplicationBasePath).AddJsonFile("config.json", true);
            
            Configuration=builder.Build();
        }
        
        public IConfiguration Configuration { get; set; }
      
        // For more information on how to configure your application, visit http://go.microsoft.com/fwlink/?LinkID=398940
        public void ConfigureServices(IServiceCollection services)
        {
            
            services.Configure<FirebaseAuth>(Configuration);
            
            services.AddMvc();
            
            services.AddScoped<IPlatoRepository, PlatoRepository>();

            
        }

        public void Configure(IApplicationBuilder app)
        {
            app.UseMvc(routes=>{
                routes.MapWebApiRoute(
                    "Api",
                    "api/{controller}/{Id}"
                );
            });
        }
    }
}
