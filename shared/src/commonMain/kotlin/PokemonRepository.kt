class PokemonRepository(private val pokemonDataSource: PokemonDataSource) {
    suspend fun getPokemon(region: Regions): List<PokemonEntry> = pokemonDataSource.fetchPokemon(region)
}