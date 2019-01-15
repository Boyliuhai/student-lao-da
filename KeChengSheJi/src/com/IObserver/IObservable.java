package com.IObserver;

/**
 * 被观察者，由他来触发更新数据操作
 * @author Jack
 */
public interface IObservable {
	//添加观察者
	void addObserver(IObserver o);
	//移除观察者
	void deleteObserver(IObserver o);
	//提醒观察者
	void notifyAllObserver();
}
