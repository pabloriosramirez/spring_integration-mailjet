package online.grisk.hermes.integration.activator;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.resource.Emailv31;
import online.grisk.hermes.domain.RequestEmail;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class EmailServiceActivator {

    private static String textResetPass = "¿Olvidaste tu contraseña? No te preocupes, podrás restablecer la contraseña de tu cuenta GRisk. Simplemente copia esta url en tu navegador: http://www.grisk.online/login/reset/TOKEN_RESET";
    private static String htmlResetPass = "<html><head> <title>Restablecer contraseña de cuenta GRisk</title> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> <meta name='viewport' content='width=device-width, initial-scale=1'> <style type='text/css'> #outlook a{padding: 0;}.ReadMsgBody{width: 100%;}.ExternalClass{width: 100%;}.ExternalClass *{line-height: 100%;}body{margin: 0; padding: 0; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}table, td{border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;}img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic;}p{display: block; margin: 13px 0;}</style> <style type='text/css'> @media only screen and (max-width: 480px){@-ms-viewport{width: 320px;}@viewport{width: 320px;}}</style><!--[if mso]> <xml> <o:OfficeDocumentSettings> <o:AllowPNG/> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml><![endif]--><!--[if lte mso 11]> <style type='text/css'> .outlook-group-fix{width: 100% !important;}</style><![endif]--> <link href='https://fonts.googleapis.com/css?family=' rel='stylesheet' type='text/css'> <link href='https://fonts.googleapis.com/css?family=Consolas' rel='stylesheet' type='text/css'> <style type='text/css'> @import url(https://fonts.googleapis.com/css?family=); @import url(https://fonts.googleapis.com/css?family=Consolas); </style> <style type='text/css'> @media only screen and (min-width: 480px){.mj-column-per-100{width: 100% !important; max-width: 100%;}}</style> <style type='text/css'> [owa] .mj-column-per-100{width: 100% !important; max-width: 100%;}</style> <style type='text/css'> @media only screen and (max-width: 480px){table.full-width-mobile{width: 100% !important;}td.full-width-mobile{width: auto !important;}}</style></head><body style='background-color:#F4F4F4;'><div style='background-color:#F4F4F4;'><!--[if mso | IE]> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='width:100%;'> <tbody> <tr> <td style='direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;padding-top:0px;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'> <tbody> <tr> <td align='center' style='background:#ffffff;font-size:0px;padding:0px 0px 0px 0px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;word-break:break-word;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='border-collapse:collapse;border-spacing:0px;margin-top: 50px;'> <tbody> <tr> <td style='width:94px;'> <a href='http://www.grisk.online' target='_blank'> <img alt='GRISK' height='auto' src='http://www.grisk.online/images/grisk.png' style='border:none;border-radius:px;display:block;outline:none;text-decoration:none;height:auto;width:100%;' title='' width='94'> </a> </td></tr></tbody> </table> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='background:#ffffff;background-color:#ffffff;Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='background:#ffffff;background-color:#ffffff;width:100%;'> <tbody> <tr> <td style='border:0px solid #ffffff;direction:ltr;font-size:0px;padding:20px 0px 20px 0px;padding-bottom:20px;padding-left:0px;padding-right:0px;padding-top:20px;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'> <tbody> <tr> <td align='left' style='font-size:0px;padding:0px 25px 0px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:13px;line-height:22px;text-align:left;color:#55575d;'> <h1 style='font-size: 20px; font-weight: bold; text-align: center;'>¿Olvidaste tu contraseña?</h1> </div></td></tr><tr> <td align='left' style='font-size:0px;padding:0px 25px 0px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:13px;line-height:22px;text-align:left;color:#55575d;'> <p style='font-size: 13px; text-align: center; margin: 10px 0;'>No te preocupes, podrás restablecer la contraseña de tu cuenta <b style='font-weight:700'>GRisk.</b> </p><p style='font-size: 13px; text-align: center; margin: 10px 0;'>Simplemente haz click en el siguiente botón:</p></div></td></tr><tr> <td align='center' vertical-align='middle' style='font-size:0px;padding:10px 25px 10px 25px;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;word-break:break-word;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='border-collapse:separate;line-height:100%;'> <tbody> <tr> <td align='center' bgcolor='#7c0acd' role='presentation' style='border:0px solid #ffffff;border-radius:3px;cursor:auto;padding:10px 25px 10px 25px;background:#7c0acd;' valign='middle'> <a href='http://www.grisk.online/login/reset/TOKEN_RESET' style='background:#7c0acd;color:#ffffff;font-family:Arial, sans-serif;font-size:13px;font-weight:normal;line-height:120%;Margin:0;text-decoration:none;text-transform:none;' target='_blank'> Restablecer contraseña </a> </td></tr></tbody> </table> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='background:#ffffff;background-color:#ffffff;Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='background:#ffffff;background-color:#ffffff;width:100%;'> <tbody> <tr> <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'> <tbody> <tr> <td align='left' style='font-size:0px;padding:10px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:13px;line-height:22px;text-align:left;color:#55575d;'> <p style='font-size: 13px; margin: 10px 0; text-align: center;'>Si el botón no funciona, copia esta url en tu navegador:</p><p style='font-size: 13px; margin: 10px 0; text-align: center;'> <a href='http://www.grisk.online/login/reset/TOKEN_RESET'>http://www.grisk.online/login/reset/TOKEN_RESET</a></p></div></td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='width:100%;'> <tbody> <tr> <td style='direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:10px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' width='100%'> <tbody> <tr> <td style='vertical-align:top;padding:0;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='' width='100%'> <tbody> <tr> <td align='center' style='font-size:0px;padding:10px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:11px;line-height:22px;text-align:center;color:#55575d;'> <p style='font-size: 10px; margin: 10px 0;'>Se envió este correo electrónico porque se ha solicitado restablecer la contraseña de su cuenta de <b style='font-weight:700;'>GRisk</b>, háganos saber si cree que este correo electrónico le fue enviado por error, enviando un correo a <b style='font-weight:700;'><span style='text-decoration: none; font-weight: 700;'>hola@grisk.online</span></b> </p><p style='font-size: 10px; margin: 0px 0;'><span style='color:#222222; font-family:Consolas,' lucida='' console','courier='' new',monospace;='' font-size:12px'=''>© 2019 GRisk | <a href='http://www.grisk.online'>www.grisk.online</a> </span> </p></div></td></tr></tbody> </table> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--><!--[if mso | IE]> </td></tr></table><![endif]--></div></body></html>";
    private static String textRegister = "Hola, necesitamos validar tu correo electrónico para poder activar tu cuenta.  Simplemente copia esta url en tu navegador: http://www.grisk.online/login/confirm/TOKEN_AUTH";
    private static String htmlRegister = "<html><head> <title>Activación de cuenta GRisk</title> <meta http-equiv='X-UA-Compatible' content='IE=edge'> <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'> <meta name='viewport' content='width=device-width, initial-scale=1'> <style type='text/css'> #outlook a{padding: 0;}.ReadMsgBody{width: 100%;}.ExternalClass{width: 100%;}.ExternalClass *{line-height: 100%;}body{margin: 0; padding: 0; -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%;}table, td{border-collapse: collapse; mso-table-lspace: 0pt; mso-table-rspace: 0pt;}img{border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; -ms-interpolation-mode: bicubic;}p{display: block; margin: 13px 0;}</style> <style type='text/css'> @media only screen and (max-width: 480px){@-ms-viewport{width: 320px;}@viewport{width: 320px;}}</style><!--[if mso]> <xml> <o:OfficeDocumentSettings> <o:AllowPNG/> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml><![endif]--><!--[if lte mso 11]> <style type='text/css'> .outlook-group-fix{width: 100% !important;}</style><![endif]--> <link href='https://fonts.googleapis.com/css?family=' rel='stylesheet' type='text/css'> <link href='https://fonts.googleapis.com/css?family=Consolas' rel='stylesheet' type='text/css'> <style type='text/css'> @import url(https://fonts.googleapis.com/css?family=); @import url(https://fonts.googleapis.com/css?family=Consolas); </style> <style type='text/css'> @media only screen and (min-width: 480px){.mj-column-per-100{width: 100% !important; max-width: 100%;}}</style> <style type='text/css'> [owa] .mj-column-per-100{width: 100% !important; max-width: 100%;}</style> <style type='text/css'> @media only screen and (max-width: 480px){table.full-width-mobile{width: 100% !important;}td.full-width-mobile{width: auto !important;}}</style></head><body style='background-color:#F4F4F4;'><div style='background-color:#F4F4F4;'><!--[if mso | IE]> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='width:100%;'> <tbody> <tr> <td style='direction:ltr;font-size:0px;padding:20px 0;padding-bottom:0px;padding-top:0px;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'> <tbody> <tr> <td align='center' style='background:#ffffff;font-size:0px;padding:0px 0px 0px 0px;padding-top:0px;padding-right:0px;padding-bottom:0px;padding-left:0px;word-break:break-word;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='border-collapse:collapse;border-spacing:0px;margin-top: 50px;'> <tbody> <tr> <td style='width:94px;'> <a href='http://www.grisk.online' target='_blank'> <img alt='GRISK' height='auto' src='http://www.grisk.online/images/grisk.png' style='border:none;border-radius:px;display:block;outline:none;text-decoration:none;height:auto;width:100%;' title='' width='94'> </a> </td></tr></tbody> </table> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='background:#ffffff;background-color:#ffffff;Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='background:#ffffff;background-color:#ffffff;width:100%;'> <tbody> <tr> <td style='border:0px solid #ffffff;direction:ltr;font-size:0px;padding:20px 0px 20px 0px;padding-bottom:20px;padding-left:0px;padding-right:0px;padding-top:20px;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'> <tbody> <tr> <td align='left' style='font-size:0px;padding:0px 25px 0px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:13px;line-height:22px;text-align:left;color:#55575d;'> <h1 style='font-size: 20px; font-weight: bold; text-align: center;'>Activa tu cuenta</h1> </div></td></tr><tr> <td align='left' style='font-size:0px;padding:0px 25px 0px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:13px;line-height:22px;text-align:left;color:#55575d;'> <p style='font-size: 13px; text-align: center; margin: 10px 0;'>Necesitamos validar tu correo electrónico para poder activar tu cuenta <b style='font-weight:700'>GRisk.</b> Simplemente haz click en el siguiente botón:</p></div></td></tr><tr> <td align='center' vertical-align='middle' style='font-size:0px;padding:10px 25px 10px 25px;padding-top:10px;padding-right:25px;padding-bottom:10px;padding-left:25px;word-break:break-word;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='border-collapse:separate;line-height:100%;'> <tbody> <tr> <td align='center' bgcolor='#7c0acd' role='presentation' style='border:0px solid #ffffff;border-radius:3px;cursor:auto;padding:10px 25px 10px 25px;background:#7c0acd;' valign='middle'> <a href='http://www.grisk.online/login/confirm/TOKEN_AUTH' style='background:#7c0acd;color:#ffffff;font-family:Arial, sans-serif;font-size:13px;font-weight:normal;line-height:120%;Margin:0;text-decoration:none;text-transform:none;' target='_blank'> Confirmar mi cuenta </a> </td></tr></tbody> </table> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='background:#ffffff;background-color:#ffffff;Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='background:#ffffff;background-color:#ffffff;width:100%;'> <tbody> <tr> <td style='direction:ltr;font-size:0px;padding:20px 0;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='vertical-align:top;' width='100%'> <tbody> <tr> <td align='left' style='font-size:0px;padding:10px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:13px;line-height:22px;text-align:left;color:#55575d;'> <p style='font-size: 13px; margin: 10px 0; text-align: center;'>Si el botón no funciona, copia esta url en tu navegador:</p><p style='font-size: 13px; margin: 10px 0; text-align: center;'> <a href='http://www.grisk.online/login/confirm/TOKEN_AUTH'>http://www.grisk.online/login/confirm/TOKEN_AUTH</a></p></div></td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--> <div style='Margin:0px auto;max-width:600px;'> <table align='center' border='0' cellpadding='0' cellspacing='0' role='presentation' style='width:100%;'> <tbody> <tr> <td style='direction:ltr;font-size:0px;padding:0px 0px 0px 0px;text-align:center;vertical-align:top;'><!--[if mso | IE]> <table role='presentation' border='0' cellpadding='0' cellspacing='0'> <tr> <td class='' style='vertical-align:top;width:600px;' ><![endif]--> <div class='mj-column-per-100 outlook-group-fix' style='font-size:10px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' width='100%'> <tbody> <tr> <td style='vertical-align:top;padding:0;'> <table border='0' cellpadding='0' cellspacing='0' role='presentation' style='' width='100%'> <tbody> <tr> <td align='center' style='font-size:0px;padding:10px 25px;padding-top:0px;padding-bottom:0px;word-break:break-word;'> <div style='font-family:Arial, sans-serif;font-size:11px;line-height:22px;text-align:center;color:#55575d;'> <p style='font-size: 10px; margin: 10px 0;'>Se envió este correo electrónico porque se registró en una cuenta de <b style='font-weight:700;'>GRisk</b>, háganos saber si cree que este correo electrónico le fue enviado por error, enviando un correo a <b style='font-weight:700;'><span style='text-decoration: none; font-weight: 700;'>hola@grisk.online</span></b> </p><p style='font-size: 10px; margin: 0px 0;'><span style='color:#222222; font-family:Consolas,' lucida='' console','courier='' new',monospace;='' font-size:12px'=''>© 2019 GRisk | <a href='http://www.grisk.online'>www.grisk.online</a> </span> </p></div></td></tr></tbody> </table> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table><![endif]--> </td></tr></tbody> </table> </div><!--[if mso | IE]> </td></tr></table> <table align='center' border='0' cellpadding='0' cellspacing='0' class='' style='width:600px;' width='600' > <tr> <td style='line-height:0px;font-size:0px;mso-line-height-rule:exactly;'><![endif]--><!--[if mso | IE]> </td></tr></table><![endif]--></div></body></html>";


    @Autowired
    UUID uuid;

    @Autowired
    MailjetClient mailjetClient;

    @Value("${MAILJET_TO}")
    String mailTo;

    @Value("${MAILJET_NAME}")
    String nameTo;

    public Message resetPassword(@Payload RequestEmail requestEmail) {
        return invoke(
                requestEmail.getAddress(),
                "Restablecer contraseña de cuenta GRisk",
                textResetPass.replaceAll("TOKEN_RESET", requestEmail.getToken()),
                htmlResetPass.replaceAll("TOKEN_RESET", requestEmail.getToken()));
    }

    public Message registerUser(@Payload RequestEmail requestEmail) {
        return invoke(
                requestEmail.getAddress(),
                "Activación de cuenta de GRisk",
                textRegister.replaceAll("TOKEN_AUTH", requestEmail.getToken()),
                htmlRegister.replaceAll("TOKEN_AUTH", requestEmail.getToken()));
    }

    private Message invoke(String address,
                           String subject,
                           String text,
                           String html) {
        JSONObject message = new JSONObject();
        message.put(Emailv31.Message.FROM, new JSONObject()
                .put(Emailv31.Message.EMAIL, mailTo)
                .put(Emailv31.Message.NAME, nameTo)
        )
                .put(Emailv31.Message.SUBJECT, subject)
                .put(Emailv31.Message.TEXTPART, text)
                .put(Emailv31.Message.HTMLPART, html)
                .put(Emailv31.Message.TO, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.EMAIL, address)));
        MailjetRequest mailjetRequest = new MailjetRequest(Emailv31.resource).property(Emailv31.MESSAGES, (new JSONArray()).put(message));

        return sendEmail(mailjetRequest);
    }

    private Message sendEmail(MailjetRequest mailjetRequest) {
        Map response = new HashMap();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        response.put("message", "Ha ocurrido un problema inesperado");
        try {
            MailjetResponse mailjetResponse = mailjetClient.post(mailjetRequest);
            if (mailjetResponse.getStatus() == 200) {
                response.put("status", HttpStatus.OK);
                response.put("message", "Se ha enviado correctamente el email.");
                return MessageBuilder.withPayload(response).build();
            } else {
                return MessageBuilder.withPayload(response).build();
            }
        } catch (Exception e) {
            return MessageBuilder.withPayload(response).build();
        }
    }
}
