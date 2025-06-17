package com.controller.controlException;

public class NotChangedEpicStatusException  extends ControlException{
    public NotChangedEpicStatusException(Integer IdTask) {
        super("NotChangedEpicStatusException: Нельзя изменить статус Эпика с " + IdTask + "! Статус рассчитывается автоматически!");
    }
}

