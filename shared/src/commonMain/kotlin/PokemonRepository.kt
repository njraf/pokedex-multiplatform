class PokemonRepository(private val pokemonDataSource: PokemonDataSource) {
    suspend fun getPokemon(region: Regions): List<PokemonEntry> = pokemonDataSource.fetchPokemon(region)

    suspend fun getPokemonDetails(name: String): PokemonDetails = pokemonDataSource.fetchPokemonDetails(name)
    suspend fun getPokemonDetails(nationalDexNumber: Int): PokemonDetails = pokemonDataSource.fetchPokemonDetails(nationalDexNumber)

    suspend fun getPokemonSpecies(name: String): PokemonSpeciesModel = pokemonDataSource.fetchPokemonSpecies(name)
}