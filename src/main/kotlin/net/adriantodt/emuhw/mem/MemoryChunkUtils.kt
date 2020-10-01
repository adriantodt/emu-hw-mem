package net.adriantodt.emuhw.mem

fun MemoryChunk.validAddressRange(addr: Int, size: Int): Boolean {
    return validAddress(addr) && validAddress(addr + size)
}

fun MemoryChunk.validAddress(addr: Int): Boolean {
    return addr - start in 0..length
}
