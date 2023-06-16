package com.huaanhuang.translate;

import javax.swing.*;

public class VolcKeyUI {
    private JPanel contentPanel;
    private JLabel appIdLabel;
    private JTextField keyInput;
    private JLabel keyLabel;
    private JTextField appIdInput;
    private JLabel tipLabel;

    public JComponent getContentPanel() {
        return contentPanel;
    }

    public void updateAppId(String appId) {
        appIdInput.setText(appId == null || appId.isEmpty() ? "" : appId);
    }

    public String getAppId() {
        return appIdInput.getText().trim();
    }

    public void updateKey(String key) {
        keyInput.setText(key == null || key.isEmpty() ? "" : key);
    }

    public String getKey() {
        return keyInput.getText().trim();
    }
}
