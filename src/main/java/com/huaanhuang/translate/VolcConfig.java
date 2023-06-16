package com.huaanhuang.translate;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class VolcConfig implements Configurable {
    private VolcKeyUI settingUI;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "VolcTranslate";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingUI = new VolcKeyUI();
        settingUI.updateAppId(PropertiesComponent.getInstance().getValue("volc_access_key", ""));
        settingUI.updateKey(PropertiesComponent.getInstance().getValue("volc_access_secret", ""));
        return settingUI.getContentPanel();
    }

    @Override
    public boolean isModified() {
        // 在用户修改设置后，该方法将被调用，可以在该方法中检查设置是否已修改
        return true;
    }

    @Override
    public void apply() {
        if (settingUI.getAppId().isEmpty() || settingUI.getKey().isEmpty()) {
            Messages.showInfoMessage("请填写火山翻译accessKey和accessSecret", "警告");
            return;
        }
        PropertiesComponent.getInstance().setValue("volc_access_key", settingUI.getAppId());
        PropertiesComponent.getInstance().setValue("volc_access_secret", settingUI.getKey());
    }

    @Override
    public void disposeUIResources() {
        settingUI = null;
    }
}
