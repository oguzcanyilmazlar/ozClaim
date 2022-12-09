package me.acablade.utils;

public class ChunkUtils {

    public static long getChunkId(int x, int z){
        return (((long)x) << 32) | (z & 0xffffffffL);
    }

    public static int[] idToChunk(long l){
        int x = (int)(l >> 32);
        int z = (int)l;
        return new int[]{x,z};
    }

}