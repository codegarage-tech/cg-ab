package tech.codegarage.apkbackup.model;

import android.support.annotation.DrawableRes;

import java.io.Serializable;

/**
 * @author Md. Rashadul Alam
 * Email: rashed.droid@gmail.com
 */
public class ApplicationInfo implements Comparable<ApplicationInfo>, Serializable {

    private String appName;
    private @DrawableRes
    int appIcon;
    private String appPackage;

    public ApplicationInfo(String appName, int appIcon, String appPackage) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.appPackage = appPackage;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(int appIcon) {
        this.appIcon = appIcon;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    @Override
    public int compareTo(ApplicationInfo applicationInfo) {
        return this.appName.compareTo(applicationInfo.getAppName());
    }
}
