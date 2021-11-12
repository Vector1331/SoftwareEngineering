from django.db import models
from django.core.validators import MinValueValidator, MaxValueValidator

class user(models.Model):
    id_user = models.AutoField(primary_key = True)
    kakaotoken = models.BooleanField()
    def __str__(self):
        return str(self.id_user)

class diary(models.Model):
    diary_id = models.AutoField(primary_key = True)
    diary_date = models.DateTimeField(auto_now_add = True)
    diary_weather = models.PositiveSmallIntegerField(validators = [MinValueValidator(1), MaxValueValidator(4)]) #1-맑음,2-흐림,3-비,4-눈
    diary_title = models.CharField(max_length = 20,)
    diary_content = models.TextField(max_length = 200)
    diary_todayme = models.CharField(max_length = 20, null = True, blank = True)
    diary_tomorrowme = models.CharField(max_length = 20, null = True, blank = True)
    diary_img = models.ImageField(upload_to = None, height_field = None, width_field = None, max_length = 100, null = True, blank = True)
#    user = models.ForeignKey(user, on_delete = models.CASCADE, null = True)
    def __str__(self):
        return self.diary_title

class calendar(models.Model):
    cal_todaysdate = models.DateTimeField(auto_now_add = True, primary_key = True)
    cal_thismonth = models.IntegerField(validators = [MinValueValidator(1), MaxValueValidator(12)], null = True) #1~12월
    cal_diaryfrequency = models.IntegerField(default = 0, null = True, blank = True)
    user = models.ForeignKey(user, on_delete = models.CASCADE, null = True)
    diary = models.OneToOneField(diary, on_delete = models.CASCADE, null = True)
    def __str__(self):
        return str(self.cal_thismonth)

