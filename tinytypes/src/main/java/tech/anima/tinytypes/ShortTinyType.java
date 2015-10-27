package tech.anima.tinytypes;

public abstract class ShortTinyType {

    public final short value;

    public ShortTinyType(short value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return (int) value; //as per implementation of Short::hashCode(short) in jdk8.0.45
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ShortTinyType other = (ShortTinyType) obj;
        return this.value == other.value;
    }

    @Override
    public String toString() {
        return String.format("%s#%s", this.getClass().getSimpleName(), value);
    }

}