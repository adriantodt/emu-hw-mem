import net.adriantodt.emuhw.mem.MMIO
import net.adriantodt.emuhw.mem.MemoryBlob
import net.adriantodt.emuhw.mem.MemoryChunk

fun main() {
    // build a tree
    val root = MMIO(MemoryBlob(0, 256))
    root.addChild(LoggingChunk(0, 1))
    root.addChild(LoggingChunk(250, 6))

    val mmioChild = MMIO(MemoryBlob(127, 64))
    mmioChild.addChild(LoggingChunk(127, 1))
    mmioChild.addChild(LoggingChunk(159, 32))

    root.addChild(mmioChild)


    // test
    for (addr in 0..256) {
        println("CURRENTLY: 0x${addr.toString(16)} [$addr]")
        root.onRead(addr)
        root.onWrite(addr, 1)
    }

}

private class LoggingChunk(override val start: Int, override val length: Int) : MemoryChunk {
    override fun onRead(addr: Int): Byte {
        println("onRead(0x${addr.toString(16)} [$addr])")
        return 0
    }

    override fun onWrite(addr: Int, value: Byte) {
        println("onWrite(0x${addr.toString(16)} [$addr], $value)")
    }
}