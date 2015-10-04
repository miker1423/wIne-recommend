using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.Framework.Caching.Memory;
using Microsoft.Framework.OptionsModel;

using FireSharp;
using FireSharp.Config;
using FireSharp.Response;

using iRecommend.Services;

namespace iRecommend.Models
{	class PlatoRepository:IPlatoRepository
	{
		private const string CacheKey=nameof(PlatoRepository);
		private const string cachKey=nameof(IPlatoRepository);
		private IMemoryCache _chache;
		private FirebaseClient client;
		
		public PlatoRepository(IOptions<FirebaseAuth> fireOp,
							   IMemoryCache cache)
		{
			client=new FirebaseClient(new FirebaseConfig{
				AuthSecret=fireOp.Options.Secret,
				BasePath=fireOp.Options.Url
			});
			_chache=cache;
			
		}
		
		public async Task<Dictionary<string,Plato>> getPlatos(){
			
			Dictionary<string, Plato> platos;
			
            if (_chache.Get(CacheKey).Equals(null))
            {
                FirebaseResponse resutl = await client.GetAsync("");
                platos = resutl.ResultAs<Dictionary<string, Plato>>();
				
				_chache.Set(CacheKey, platos);
            }
			else{
				platos=(Dictionary<string,Plato>)_chache.Get(CacheKey);
			}
			
			return platos;
		}
		
		public async Task<List<string>> getNames(List<string> Ids){
			List<string> vinos=new List<string>();
			ParallelLoopResult result=Parallel.ForEach(await getPlatos(), (vino)=>{
				foreach(Tipo t in vino.Value.tipos){
					string wine=t.Nombre;
					foreach(Uva u in t.uvas){
						wine=wine+u.Nombre;
						vinos.Add(wine);
					}
				}
			});
			return vinos;
		} 
		
		public async Task<List<string>> LookFor(string[] Platillos ){
			
			List<string> lista=new List<string>();
			
			ParallelLoopResult result=Parallel.ForEach(await getPlatos(), (Pair)=>{
				string s=Pair.Key;
				Plato p=Pair.Value;
				foreach(string st in Platillos){
					if(s==p.Nombre){
						lista.Add(s);
					}
				}
			});
			lista=await getNames(lista);
			return lista;
		}
    }
}