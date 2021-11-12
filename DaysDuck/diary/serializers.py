from rest_framework import serializers

from .models import calendar, user, diary

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = user
        fields = ('id_user', 'kakaotoken',)

class DiarySerializer(serializers.ModelSerializer):
    class Meta:
        model = diary 
        fields = ('diary_id', 'diary_title', 'diary_date', 'diary_weather', 'diary_content', 'diary_todayme', 'diary_tomorrowme', 'diary_img', )

class CalenderSerializer(serializers.ModelSerializer):
    class Meta:
        model = calendar
        fields = ('cal_todaysdate', 'cal_thismonth', 'cal_todaysdate', 'cal_diaryfrequency', )
        