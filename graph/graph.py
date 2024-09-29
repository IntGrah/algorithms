from collections.abc import Mapping, Iterable


type Graph[K] = Mapping[K, Iterable[K]]
type WeightedGraph[K, W] = Mapping[K, Mapping[K, W]]
