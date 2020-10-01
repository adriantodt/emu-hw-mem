package net.adriantodt.emuhw.mem

class MemoryBlob(override val start: Int, override val length: Int) : MemoryChunk {
    private val blob = ByteArray(length)

    override fun onRead(addr: Int): Byte {
        require(validAddress(addr)) { "Address 0x${addr.toString(16)} is outside of range (0x${start.toString(16)} to 0x${(start + length).toString(16)})" }
        return blob[addr - start]
    }

    override fun onWrite(addr: Int, value: Byte) {
        require(validAddress(addr)) { "Address 0x${addr.toString(16)} is outside of range (0x${start.toString(16)} to 0x${(start + length).toString(16)})" }
        blob[addr - start] = value
    }
}