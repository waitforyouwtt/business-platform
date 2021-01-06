package com.business.service.impl;

import com.business.request.EmailParam;
import com.business.request.RequestEmail;
import com.business.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 凤凰小哥哥
 * @date 2020-12-30
 */
@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Value("${file.rootPath}")
    private String filePath;

    @Resource
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void simpleSend(RequestEmail vo){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发送者
            helper.setFrom(vo.getEmailSender());
            //接收者邮箱
            String[] recipientsList = new String[vo.getEmailRecipients().size()];
            vo.getEmailRecipients().toArray(recipientsList);
            helper.setTo(recipientsList);
            //抄送者邮箱
            if (!CollectionUtils.isEmpty(vo.getEmailCopyPersons())){
                String[] copyPersonList = new String[vo.getEmailCopyPersons().size()];
                vo.getEmailCopyPersons().toArray(copyPersonList);
                //抄送人，使用Cc还是Bcc大家根据具体场景自己选择
                helper.setCc(copyPersonList);
                //秘密抄送（发件人，收件人，抄送人都不会看到抄送给谁了）
                helper.setBcc(copyPersonList);
            }
            //邮件主题
            helper.setSubject(vo.getEmailSubject());
            //邮件内容
            helper.setText(vo.getEmailContent());
            mailSender.send(message);
        } catch (Exception e) {
            log.info("[邮件发送异常]：邮件文字发送异常信息：{}",e.getMessage());
        }
    }

    @Override
    public void enclosureSend(RequestEmail vo){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper=new MimeMessageHelper(message,true);
            //发送者
            helper.setFrom(vo.getEmailSender());
            //接收者邮箱
            String[] recipientsList = new String[vo.getEmailRecipients().size()];
            vo.getEmailRecipients().toArray(recipientsList);
            helper.setTo(recipientsList);
            //抄送者邮箱
            if (!CollectionUtils.isEmpty(vo.getEmailCopyPersons())){
                String[] copyPersonList = new String[vo.getEmailCopyPersons().size()];
                vo.getEmailCopyPersons().toArray(copyPersonList);
                //抄送人，使用Cc还是Bcc大家根据具体场景自己选择
                helper.setCc(copyPersonList);
                //秘密抄送（发件人，收件人，抄送人都不会看到抄送给谁了）
                //helper.setBcc(copyPersonList);
            }
            //邮件主题
            helper.setSubject(vo.getEmailSubject());
            //邮件内容
            helper.setText(vo.getEmailContent());
            //附件集合
            List<String> emailFileList = new ArrayList<>();

            List<MultipartFile> files = vo.getFiles();
            if (!CollectionUtils.isEmpty(files)) {
                for (MultipartFile file : files) {
                    String fileName = file.getOriginalFilename();
                    File dest = new File(filePath + fileName);
                    file.transferTo(dest);
                    emailFileList.add(filePath + fileName);
                }
            }
            log.info("文件集合：{}",emailFileList);

            if (!CollectionUtils.isEmpty( emailFileList )){
                FileSystemResource file;

                String[] fileList = new String[emailFileList.size()];
                emailFileList.toArray(fileList);

                for (int i = 0; i < fileList.length; i++) {
                    //添加附件
                    file=new FileSystemResource(fileList[i]);
                    helper.addAttachment(fileList[i].substring(fileList[i].lastIndexOf(File.separator)+1), file);
                }
            }
            mailSender.send(message);
        } catch (Exception e) {
            log.info("[邮件发送异常]：邮件多人多邮件带抄送人发送异常信息：{}",e.getMessage());
        }
    }

    @Async
    @Override
    public void modelSend(EmailParam vo){
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发送者
            helper.setFrom(vo.getEmailSender());
            //接收者邮箱
            String[] recipientsList = new String[vo.getEmailRecipients().size()];
            vo.getEmailRecipients().toArray(recipientsList);
            helper.setTo(recipientsList);
            //抄送者邮箱
            if (!CollectionUtils.isEmpty(vo.getEmailCopyPersons())){
                String[] copyPersonList = new String[vo.getEmailCopyPersons().size()];
                vo.getEmailCopyPersons().toArray(copyPersonList);
                //抄送人，使用Cc还是Bcc大家根据具体场景自己选择
                helper.setCc(copyPersonList);
                //秘密抄送（发件人，收件人，抄送人都不会看到抄送给谁了）
                helper.setBcc(copyPersonList);
            }
            //邮件主题
            helper.setSubject(vo.getEmailSubject());
            //邮件内容：利用 Thymeleaf 模板构建 html 文本
            Context ctx = new Context();
            // 给模板的参数的上下文
            ctx.setVariable("customer", vo);
            // 执行模板引擎，执行模板引擎需要传入模板名、上下文对象
            // Thymeleaf的默认配置期望所有HTML文件都放在 **resources/templates ** 目录下，以.html扩展名结尾。
            // String emailText = templateEngine.process("email/templates", ctx);
            String emailText = templateEngine.process(vo.getTemplate(), ctx);
            helper.setText(emailText, true);
            //绝对路径
           /* FileSystemResource bankLogoImage = new FileSystemResource(vo.getBackgroundUrlPath());
            helper.addInline("logoImage", bankLogoImage);*/
            mailSender.send(message);
        } catch (Exception e) {
            log.info("[邮件发送异常]：邮件文字发送异常信息：{}",e.getMessage());
        }
    }

}
