package datastructures;

public interface DataStructure {
    @SuppressWarnings("unchecked")
    static <C extends DataStructure> C convert(DataStructure dt) {
        return (C) dt;
    }
}
