DATABASES = {
    'default' : {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'billow', # 연동할 MYSQL의 DB명
        'USER': 'B309', # DB 접속 계정 명
        'PASSWORD': 'B309Billow', # 해당 DB 접속 계정 비밀번호
        'HOST': 'localhost', # 실제 DB 주소
        'PORT': '3306' # 포트 번호
    }   
}
SECRET_KEY = 'django-insecure-h9a#$_0rrkpqdc$=e2ed123*cdk$l&wm*yc%mwv210y=9p5tp+'