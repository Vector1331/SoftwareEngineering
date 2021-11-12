from django.contrib import admin

from .models import user
from .models import diary
from .models import calendar

admin.site.register(user)
admin.site.register(diary)
admin.site.register(calendar)
