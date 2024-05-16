from rest_framework import serializers
from .models import Usuario

class LoginSerializer(serializers.ModelSerializer):
    class Meta:
        model = Usuario
        fields = ["email", "password"]