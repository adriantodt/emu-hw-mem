package net.adriantodt.emuhw.mem

interface MemoryChunk {
    val start: Int

    val length: Int

    fun onRead(addr: Int): Byte

    fun onWrite(addr: Int, value: Byte)
}