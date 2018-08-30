package com.limin.myapplication3.refresh.constant;

/**
 * 尺寸值的定义状态，用于在值覆盖的时候决定优先级
 * 越往下优先级越高
 */
public enum DimensionStatus {
    /**
     * 默认值，但是还没通知确认
     */
    DefaultUnNotify(false),
    /**
     * 默认值
     */
    Default(true),
    /**
     * Xml计算，但是还没通知确认
     */
    XmlWrapUnNotify(false),
    /**
     * Xml计算
     */
    XmlWrap(true),
    /**
     * Xml 的view 指定，但是还没通知确认
     */
    XmlExactUnNotify(false),
    /**
     * Xml 的view 指定
     */
    XmlExact(true),
    /**
     * Xml 的layout 中指定，但是还没通知确认
     */
    XmlLayoutUnNotify(false),
    /**
     * Xml 的layout 中指定
     */
    XmlLayout(true),
    /**
     * 代码指定，但是还没通知确认
     */
    CodeExactUnNotify(false),
    /**
     * 代码指定
     */
    CodeExact(true),
    /**
     * 锁死，但是还没通知确认
     */
    DeadLockUnNotify(false),
    /**
     * 锁死
     */
    DeadLock(true);
    public final boolean notified;

    DimensionStatus(boolean notified) {
        this.notified = notified;
    }

    /**
     * 转换为未通知状态
     * @return 未通知状态
     */
    public DimensionStatus unNotify() {
        if (notified) {
            DimensionStatus prev = values()[ordinal() - 1];
            if (!prev.notified) {
                return prev;
            }
            return DefaultUnNotify;
        }
        return this;
    }

    /**
     * 转换为通知状态
     * @return 通知状态
     */
    public DimensionStatus notified() {
        if (!notified) {
            return values()[ordinal() + 1];
        }
        return this;
    }

    /**
     * 是否可以被新的状态替换
     * @param status 新转台
     * @return 小于等于
     */
    public boolean canReplaceWith(DimensionStatus status) {
        return ordinal() < status.ordinal() || ((!notified || CodeExact == this) && ordinal() == status.ordinal());
    }

//    /**
//     * 是否没有达到新的状态
//     * @param status 新转台
//     * @return 大于等于 gte
//     */
//    public boolean gteStatusWith(DimensionStatus status) {
//        return ordinal() >= status.ordinal();
//    }
}