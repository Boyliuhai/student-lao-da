package com.IObserver;

/**
 * 观察者接口
 * @author Jack
 */
public interface IObserver {
	//观察者执行更新操作（参数为被观察者）
	void update(IObservable o);
}
