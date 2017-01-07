package hydra.filter.inter;

public interface Filter {
	public <T> T doFilter(T o);
}
