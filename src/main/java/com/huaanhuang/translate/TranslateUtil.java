package com.huaanhuang.translate;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.ui.Messages;
import com.volcengine.model.request.translate.TranslateTextRequest;
import com.volcengine.model.response.translate.TranslateTextResponse;
import com.volcengine.service.translate.ITranslateService;
import com.volcengine.service.translate.impl.TranslateServiceImpl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class TranslateUtil {

    static String fetchTranslateResult(String query, String to) {
        String accessKey = PropertiesComponent.getInstance().getValue("volc_access_key", "");
        String secretKey = PropertiesComponent.getInstance().getValue("volc_access_secret", "");
        if (accessKey.isEmpty() || secretKey.isEmpty()) {
            Messages.showInfoMessage("请在setting填写火山翻译API的accessKey和accessSecret", "警告");
            return null;
        }
        ITranslateService translateService = TranslateServiceImpl.getInstance();
        translateService.setAccessKey(accessKey);
        translateService.setSecretKey(secretKey);
        String result = "transaction error";
        try {
            TranslateTextRequest translateTextRequest = new TranslateTextRequest();
//            translateTextRequest.setSourceLanguage(from);
            translateTextRequest.setTargetLanguage(to);
            translateTextRequest.setTextList(Collections.singletonList(query));

            TranslateTextResponse translateText = translateService.translateText(translateTextRequest);
            List<TranslateTextResponse.Translation> translationList = translateText.getTranslationList();
            if (translationList.size() > 0) {
                result = translationList.get(0).getTranslation();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}