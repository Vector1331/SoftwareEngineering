from django.urls import path

from . import views

app_name = 'diary'

urlpatterns = [
#    path('', views.index, name='index'),
    path('<int:id_user>/home/', views.DiaryViewSet, name = 'DiaryViewSet'),
    path('<int:id_user>/calendar/', views.CalendarViewSet, name = 'CalendarViewSet'),
]
