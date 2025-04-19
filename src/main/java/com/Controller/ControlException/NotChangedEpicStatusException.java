package com.Controller.ControlException;

public class NotChangedEpicStatusException  extends ControlException{
    public NotChangedEpicStatusException() {
        super("NotChangedEpicStatusException: Нельзя изменить статус Эпика! Статус рассчитывается автоматически!");
    }
    public NotChangedEpicStatusException(Integer IdTask) {
        super("NotChangedEpicStatusException: Нельзя изменить статус Эпика с " + IdTask + "! Статус рассчитывается автоматически!");
    }
}

