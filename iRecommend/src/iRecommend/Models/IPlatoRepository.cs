using System.Threading.Tasks;
using System.Collections.Generic;

namespace iRecommend.Models
{
	public interface IPlatoRepository
	{	 
		 Task<List<string>> LookFor(string[] Platillos);
	}
}