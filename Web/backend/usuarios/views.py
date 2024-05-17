from django.shortcuts import render
import json
from .models import Usuario
from django.http import JsonResponse

def login_view(request):
    if request.method == 'POST':
        usuario = json.loads(request.body)
        email = usuario.get('email')
        password = usuario.get('password')
        user = Usuario.check_credentials(email, password)
        
        print("User: ", user)
        if user is not None:
            id_rol = user.id_rol.id_rol                               
            user_data = {
                'id' : user.id_user,
                'id_rol': id_rol,
                'nombre' : user.nombre,
                'apellido' : user.apellido,
                'email': user.email,
                'fec_nac' : user.fec_nac,
                'dni' : user.dni,
                'tel' : user.tel   
            }
            
            data = {
                'user': user_data
            }
            
            print(data)
            return JsonResponse(data)
        else:
            # Usuario o contraseña inválidos
            return JsonResponse({'error': 'Credenciales inválidas'}, status=400)
            
    return JsonResponse({'error': 'Metodo no permitido'}, status=405)
