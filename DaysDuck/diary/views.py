from django.shortcuts import render
from rest_framework import viewsets
from django.http import JsonResponse

from .models import user, diary, calendar
from .serializers import UserSerializer, DiarySerializer, CalenderSerializer

class UserViewSet(viewsets.ModelViewSet):
    queryset = user.objects.all()
    serializer_class = UserSerializer

class DiaryViewSet(viewsets.ModelViewSet):
    queryset = diary.objects.order_by('-diary_date'[::]) #내림차순으로 모든 일기목록 가져오기
    serializer_class = DiarySerializer

class CalendarViewSet(viewsets.ModelViewSet):
    queryset = calendar.objects.all()
    serializer_class = CalenderSerializer