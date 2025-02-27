package com.config;

import org.aeonbits.owner.Config;
import org.checkerframework.checker.units.qual.K;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({ "system:properties", "classpath:config.properties", "classpath:allure.properties"})
public interface Configuration extends Config {

    @Key("allure.results.directory")
    String allureResultsDir();

    @Key("base.url")
    String appUrl();

    @Key("base.test.video.path")
    String videoResultDir();

    @Key("browser")
    String browserToLaunch();

    @Key("headless")
    boolean isHeadlessRun();

    @Key("slow.motion")
    int slowMotionTimeForBrowser();

    @Key("timeout")
    int timeOutForAction();

    @Key("video")
    boolean isNeedToRecordVideo();

    @Key("username")
    String userName();

    @Key("userPassword")
    String userPassword();

    @Key("isUserNeedToBeLogin")
    boolean isUserNeedToBeLogin();
}
