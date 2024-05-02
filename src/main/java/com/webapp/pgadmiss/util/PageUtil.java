package com.webapp.pgadmiss.util;

public class PageUtil {

    /**
     * 
     * @param pageSize
     * @param totalCount
     * @return
     */
    public static long getTotalPage(int pageSize, long totalCount) {
        return (totalCount + pageSize - 1) / pageSize;
    }


    /**
     * 
     * @param pageNo
     * @param totalPage
     * @return
     */
    public static int covertPageNoToCurPage(int pageNo, int totalPage) {
		if (totalPage <= 0) {
			return 0;
		}
		if (pageNo >= totalPage) {
			return totalPage - 1;
		} else if (pageNo > 0) {
			return pageNo - 1;
		}
		return 0;
	}

}
