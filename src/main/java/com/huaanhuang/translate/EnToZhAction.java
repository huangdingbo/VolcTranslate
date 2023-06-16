package com.huaanhuang.translate;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.JBColor;

import java.awt.*;

// 中文翻译英文
public class EnToZhAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor editor = e.getData(CommonDataKeys.EDITOR);
        if (editor == null) return;
        SelectionModel selectionModel = editor.getSelectionModel();
        String selectedText = selectionModel.getSelectedText();
        if (selectedText == null) return;
        Runnable writeAction = () -> {
            // 根据空格分割成单词数组，遍历首字母大写拼接
            String translationResult = TranslateUtil.fetchTranslateResult(selectedText, "zh");

            // 气泡展示
            if (!StringUtil.isEmpty(translationResult)) {
                JBPopupFactory factory = JBPopupFactory.getInstance();
                BalloonBuilder builder = factory.createHtmlTextBalloonBuilder(translationResult, null,
                        new JBColor(new Color(188, 238, 188), new Color(73, 120, 73)), null);
                builder.createBalloon().show(
                        factory.guessBestPopupLocation(editor), Balloon.Position.below
                );
            }
        };
        WriteCommandAction.runWriteCommandAction(editor.getProject(), writeAction);
    }
}