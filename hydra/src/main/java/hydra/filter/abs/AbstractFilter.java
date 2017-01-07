package hydra.filter.abs;

import hydra.filter.inter.Filter;

public abstract class AbstractFilter implements Filter {
	
	private Filter nextFilter;
	
	public AbstractFilter(Filter nextFilter){
		this.nextFilter=nextFilter;
	}
	public <T> T doFilter(T o) {
		// TODO Auto-generated method stub
		if(nextFilter!=null){
			return nextFilter.doFilter(do_Filter(o));
		}else{
			return do_Filter(o);
		}
	}
	protected abstract <T> T do_Filter(T o);
}
