package HarmonyMuse;

/**
 * An interface that can be implemented to voice a chord.
 * This concept is similar to drawing a shape and depends greatly on the
 * context of the voicing. A guitar voicing may differ greatly from piano
 * voicing of the same chord, which may be drastically different still from
 * how a composer might orchestrate a chord for a trombone section.
 *
 */
public interface Voicing {

    public abstract void voiceChord(); // no implementation
}
