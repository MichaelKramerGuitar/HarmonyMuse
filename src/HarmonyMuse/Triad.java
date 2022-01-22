package HarmonyMuse;

public interface Triad<T> {

    public abstract T getThird();

    public abstract T getFifth();

    public abstract void setRoot(Note note);

    public abstract void setThird(Note note);

    public abstract void setFifth(Note note);

}
