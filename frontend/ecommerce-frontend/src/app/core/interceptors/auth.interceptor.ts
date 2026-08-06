import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';

import { AuthService } from '../../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService);

  const token = authService.getToken();

  // No añadir token al login ni al registro
  if (
    req.url.includes('/api/auth/login') ||
    req.url.includes('/api/auth/register')
  ) {
    return next(req);
  }

  // Si existe token, añadirlo
  if (token) {

    const clonedRequest = req.clone({

      setHeaders: {

        Authorization: `Bearer ${token}`

      }

    });

    return next(clonedRequest);

  }

  return next(req);

};
