package com.IObserver;

/**
 * ���۲��ߣ������������������ݲ���
 * @author Jack
 */
public interface IObservable {
	//��ӹ۲���
	void addObserver(IObserver o);
	//�Ƴ��۲���
	void deleteObserver(IObserver o);
	//���ѹ۲���
	void notifyAllObserver();
}
