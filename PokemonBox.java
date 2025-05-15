
public class PokemonBox {
    public static final int DEFAULT_CAPACITY = 10;

    private Pokemon[] caught;
    private int numCaught;

    public PokemonBox(Pokemon[] caught) {
        if(caught == null || caught.length == 0) {
            throw new IllegalArgumentException("Invalid Pokemon array provided.");
        }
        this.numCaught = caught.length;
        this.caught = this.deepCopyArray(caught, this.numCaught * 2);
    }

    public PokemonBox() {
        this.caught = new Pokemon[DEFAULT_CAPACITY];
        this.numCaught = 0;
    }

    public int getLocation(String pokemonName) {
        for (int i = 0; i < this.numCaught; i++) {
            if (this.caught[i].getName().equalsIgnoreCase(pokemonName)) {
                return i;
            }
        }
        return -1;
    }

    public Pokemon getPokemon(int location) {
        if (location < 0 || location >= numCaught) {
            throw new IndexOutOfBoundsException("Invalid index for getPokemon");
        }
        return this.caught[location];
    }

    public int getNumCaught() {
        return this.numCaught;
    }

    public boolean isEmpty() {
        return this.numCaught == 0;
    }

    public boolean hasPokemon(String pokemonName) {
        return this.getLocation(pokemonName) != -1;
    }

    public void add(Pokemon newPoke) throws PokemonAlreadyExistsException {
        if (this.hasPokemon(newPoke.getName())) {
            throw new PokemonAlreadyExistsException("This Pokemon already exists in your box.");
        }
        if (this.numCaught == this.caught.length) {
            this.caught = this.deepCopyArray(this.caught, this.numCaught * 2);
        }
        this.caught[this.numCaught] = new Pokemon(newPoke);
        this.numCaught++;
    }

    public String toString() {
        if(this.isEmpty()) {
            return "This box is empty";
        } else {
            String all = "\t01. " + this.caught[0].toRow();
            for(int i = 1; i < this.numCaught; i++) {
                all += String.format("%n\t%02d. %s", i+1, this.caught[i].toRow());
            }
            return String.format("This box has %d Pokemon, which are:%n%s", this.numCaught, all);
        }
    }

    private Pokemon[] deepCopyArray(Pokemon[] p, int newLength) {
        Pokemon[] deepCopy = new Pokemon[newLength];
        for(int i = 0; i < p.length && p[i] != null; i++) {
            deepCopy[i] = new Pokemon(p[i]);
        }
        return deepCopy;
    }
}
