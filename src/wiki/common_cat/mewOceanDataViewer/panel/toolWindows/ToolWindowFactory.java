package wiki.common_cat.mewOceanDataViewer.panel.toolWindows;

import wiki.common_cat.mewOceanDataViewer.core.Core;
import wiki.common_cat.mewOceanDataViewer.panel.PIX;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author common-cat
 * @version 1.00
 */
public class ToolWindowFactory {
    public static final int TEXT_WINDOW=1;
    //窗体类型
    public static final int FILE_SELECTOR=2;
    public static final int FILES_SELECTOR=3;
    public static final int LOADING=4;
    public static final int LOADED=5;
    public static final int FILE_TEXT_WINDOW=6;

    protected Core core;
    protected Font font;
    protected BufferedImage icon;
    private ToolWindowFactory(){

    }
    public static ToolWindowFactory newInstance(Core core, Font font, BufferedImage icon){
        ToolWindowFactory toolWindowFactory=new ToolWindowFactory();
        toolWindowFactory.core=core;
        toolWindowFactory.font=font;
        toolWindowFactory.icon=icon;
        return toolWindowFactory;
    }
    protected AbstractToolWindow abstractToolWindow;
    public AbstractToolWindow createNewToolWindow(int type, String title, int width, int height, Object... obj){
        this.core=core;
        switch (type){
            case TEXT_WINDOW:
                ((PIX)obj[1]).setSize(14);
                abstractToolWindow=new TEXTToolWindow(font,(Image)obj[2],width,height,icon,title,(String) obj[0]);
                return abstractToolWindow;
            case FILE_SELECTOR:
                JFileChooser fileChooser;
                fileChooser=new JFileChooser();
                fileChooser.setMultiSelectionEnabled(true);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                JButton button=new JButton("选择完毕");
                button.setBackground(Color.WHITE);
                java.util.List<String> paths0=new ArrayList();
                abstractToolWindow=new FileSelectorWindow(font,icon,"选择数据文件",fileChooser,button);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abstractToolWindow.setVisible(false);
                        AbstractToolWindow lo=ToolWindowFactory.this.createNewToolWindow(LOADING,"加载中...");
                        try {
                            for(File file:fileChooser.getSelectedFiles()){
                                paths0.add(file.getAbsolutePath());
                            }
                            core.readFiles(paths0,(TEXTToolWindow)lo);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (FontFormatException fontFormatException) {
                            fontFormatException.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        } catch (InvocationTargetException invocationTargetException) {
                            invocationTargetException.printStackTrace();
                        } catch (NoSuchMethodException noSuchMethodException) {
                            noSuchMethodException.printStackTrace();
                        } catch (InstantiationException instantiationException) {
                            instantiationException.printStackTrace();
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                });
                return abstractToolWindow;
            case FILES_SELECTOR:
                JFileChooser fileChooser0;
                fileChooser0=new JFileChooser();
                fileChooser0.setMultiSelectionEnabled(true);
                fileChooser0.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                JButton button0=new JButton("选择完毕");
                button0.setBackground(Color.WHITE);
                java.util.List<String> paths=new LinkedList<>();
                button0.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        abstractToolWindow.setVisible(false);
                        AbstractToolWindow lo1=ToolWindowFactory.this.createNewToolWindow(LOADING,"加载中...");
                        try {
                            for(File dir:fileChooser0.getSelectedFiles()){{
                                for(File file:dir.listFiles()){
                                    paths.add(file.getAbsolutePath());
                                }
                            }
                            }
                            core.readFiles(paths,(TEXTToolWindow)lo1);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        } catch (FontFormatException fontFormatException) {
                            fontFormatException.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        } catch (InvocationTargetException invocationTargetException) {
                            invocationTargetException.printStackTrace();
                        } catch (NoSuchMethodException noSuchMethodException) {
                            noSuchMethodException.printStackTrace();
                        } catch (InstantiationException instantiationException) {
                            instantiationException.printStackTrace();
                        } catch (IllegalAccessException illegalAccessException) {
                            illegalAccessException.printStackTrace();
                        }
                    }
                });
                abstractToolWindow=new FileSelectorWindow(font,icon,"选择数据文件",fileChooser0,button0);
                return abstractToolWindow;
            case LOADING:
                abstractToolWindow=new TEXTToolWindow(font,icon,200,150,icon,"加载中...","错误");
                return abstractToolWindow;
                case LOADED:
                abstractToolWindow=new TEXTToolWindow(font,icon,200,150,icon,"加载完毕","错误");
                return abstractToolWindow;
            case FILE_TEXT_WINDOW:
                abstractToolWindow=new FilePIXTEXTWindow(font,icon,width,height,icon,(String)obj[0],title);
                default:

        }
        return null;
    }
    //获得新的工具窗口
    public AbstractToolWindow createNewToolWindow(int type,String title,Object... obj){
        return createNewToolWindow(type,title,600,400,obj);
    }
}
