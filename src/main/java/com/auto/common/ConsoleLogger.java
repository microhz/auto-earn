package com.auto.common;

import com.github.kiulian.downloader.log.Logger;

/**
 * @author : jihai
 * @date : 2020/7/10
 * @description : 控制台输出
 */
public class ConsoleLogger implements Logger {

    @Override
    public void print(String content) {
        LogUtils.print(content);
    }
}
