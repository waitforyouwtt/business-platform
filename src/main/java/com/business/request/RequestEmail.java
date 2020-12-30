package com.business.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-30
 */
@Data
public class RequestEmail implements Serializable {

    @ApiModelProperty("发送者邮箱")
    private String emailSender;

    @ApiModelProperty("接受者邮箱")
    private List<String> emailRecipients;

    @ApiModelProperty("抄送人")
    private List<String> emailCopyPersons;

    @ApiModelProperty("邮件主题")
    private String emailSubject;

    @ApiModelProperty("邮件内容")
    private String emailContent;

    @ApiModelProperty("附件地址")
    private List<String> emailFiles;

    @ApiModelProperty("多个附件")
    private List<MultipartFile> files;

    @ApiModelProperty("单个附件")
    private MultipartFile multipartFile;

    @ApiModelProperty("模板名称")
    private String template;
}
