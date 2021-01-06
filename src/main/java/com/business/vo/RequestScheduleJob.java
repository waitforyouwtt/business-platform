package com.business.vo;

import com.business.entity.ScheduleJob;
import lombok.Data;

/**
 * @author 凤凰小哥哥
 * @date 2021-01-04
 */
@Data
public class RequestScheduleJob extends ScheduleJob {

    private Integer pageNum;

    private Integer pageSize;

    public int getPageNum() {
        if (pageNum == null || pageNum == 0){
            this.pageNum = 1;
        }
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageNum == 0){
            this.pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
