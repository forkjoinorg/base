package org.forkjoin.util;

/**
 *
 * 
 * @author zuoge85
 *
 */
public final class BitUtils {
	/**
	 * 
	 * @param mask
	 * @param bit 第几位,1开始
	 * @return
	 */
	public static final int set(int mask,int bit){
		return mask | (1<< (bit-1) );
	}
	/**
	 * 
	 * @param mask
	 * @param bit 第几位,1开始
	 * @return
	 */
	public static final int clear(int mask,int bit){
		return mask & ~(1<<(bit-1));
	}
	/**
	 * 
	 * @param mask
	 * @param bit 第几位,1开始
	 * @return
	 */
	public static final boolean get(int mask,int bit){
		return (mask&(1<<(bit-1))) >0;
	}
}
