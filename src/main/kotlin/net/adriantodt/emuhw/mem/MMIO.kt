package net.adriantodt.emuhw.mem

class MMIO(private val parent: MemoryBlob) : MemoryChunk {
    private val children = LinkedHashSet<MemoryChunk>()
    private val lookup = ArrayList<Pair<IntRange, MemoryChunk>>()

    override val start: Int
        get() = parent.start
    override val length: Int
        get() = parent.length

    override fun onRead(addr: Int): Byte {
        lookup.find { (it, _) ->  addr in it }?.let { (_, it) ->
            return it.onRead(addr)
        }
        return parent.onRead(addr)
    }

    override fun onWrite(addr: Int, value: Byte) {
        lookup.find { (it, _) -> addr in it }?.let { (_, it) ->
            it.onWrite(addr,value)
            return
        }
        parent.onWrite(addr, value)
    }

    fun addChild(chunk: MemoryChunk) {
        children.add(chunk)
        rebuildLookup()
    }

    private fun rebuildLookup() {
        lookup.clear()
        children.forEach {
            lookup += it.start..it.start + it.length to it
        }
    }
}