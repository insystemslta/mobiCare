package mz.co.insystems.mobicare.util;

/**
 * Created by Voloide Tamele on 10/20/2017.
 */
public final  class Constants {
    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_SWITCHING_PROTOCOLS = 101;
    public static final int HTTP_PROCESSING = 102;            // RFC2518

    // Success

    /**
     * The request has succeeded
     */
    public static final int HTTP_OK = 200;

    /**
     * The server successfully created a new resource
     */
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_ACCEPTED = 202;
    public static final int HTTP_NON_AUTHORITATIVE_INFORMATION = 203;

    /**
     * The server successfully processed the request, though no content is returned
     */
    public static final int HTTP_NO_CONTENT = 204;
    public static final int HTTP_RESET_CONTENT = 205;
    public static final int HTTP_PARTIAL_CONTENT = 206;
    public static final int HTTP_MULTI_STATUS = 207;          // RFC4918
    public static final int HTTP_ALREADY_REPORTED = 208;      // RFC5842
    public static final int HTTP_IM_USED = 226;               // RFC3229

    // Redirection

    public static final int HTTP_MULTIPLE_CHOICES = 300;
    public static final int HTTP_MOVED_PERMANENTLY = 301;
    public static final int HTTP_FOUND = 302;
    public static final int HTTP_SEE_OTHER = 303;

    /**
     * The resource has not been modified since the last request
     */
    public static final int HTTP_NOT_MODIFIED = 304;
    public static final int HTTP_USE_PROXY = 305;
    public static final int HTTP_RESERVED = 306;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    public static final int HTTP_PERMANENTLY_REDIRECT = 308;  // RFC7238

    // Client Error

    /**
     * The request cannot be fulfilled due to multiple errors
     */
    public static final int HTTP_BAD_REQUEST = 400;

    /**
     * The user is unauthorized to access the requested resource
     */
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_PAYMENT_REQUIRED = 402;

    /**
     * The requested resource is unavailable at this present time
     */
    public static final int HTTP_FORBIDDEN = 403;

    /**
     * The requested resource could not be found
     *
     * Note: This is sometimes used to mask if there was an UNAUTHORIZED (401) or
     * FORBIDDEN (403) error, for security reasons
     */
    public static final int HTTP_NOT_FOUND = 404;

    /**
     * The request method is not supported by the following resource
     */
    public static final int HTTP_METHOD_NOT_ALLOWED = 405;

    /**
     * The request was not acceptable
     */
    public static final int HTTP_NOT_ACCEPTABLE = 406;
    public static final int HTTP_PROXY_AUTHENTICATION_REQUIRED = 407;
    public static final int HTTP_REQUEST_TIMEOUT = 408;

    /**
     * The request could not be completed due to a conflict with the current state
     * of the resource
     */
    public static final int HTTP_CONFLICT = 409;
    public static final int HTTP_GONE = 410;
    public static final int HTTP_LENGTH_REQUIRED = 411;
    public static final int HTTP_PRECONDITION_FAILED = 412;
    public static final int HTTP_REQUEST_ENTITY_TOO_LARGE = 413;
    public static final int HTTP_REQUEST_URI_TOO_LONG = 414;
    public static final int HTTP_UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int HTTP_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    public static final int HTTP_EXPECTATION_FAILED = 417;
    public static final int HTTP_I_AM_A_TEAPOT = 418;                                               // RFC2324
    public static final int HTTP_UNPROCESSABLE_ENTITY = 422;                                        // RFC4918
    public static final int HTTP_LOCKED = 423;                                                      // RFC4918
    public static final int HTTP_FAILED_DEPENDENCY = 424;                                           // RFC4918
    public static final int HTTP_RESERVED_FOR_WEBDAV_ADVANCED_COLLECTIONS_EXPIRED_PROPOSAL = 425;   // RFC2817
    public static final int HTTP_UPGRADE_REQUIRED = 426;                                            // RFC2817
    public static final int HTTP_PRECONDITION_REQUIRED = 428;                                       // RFC6585
    public static final int HTTP_TOO_MANY_REQUESTS = 429;                                           // RFC6585
    public static final int HTTP_REQUEST_HEADER_FIELDS_TOO_LARGE = 431;                             // RFC6585

    // Server Error

    /**
     * The server encountered an unexpected error
     *
     * Note: This is a generic error message when no specific message
     * is suitable
     */
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;

    /**
     * The server does not recognise the request method
     */
    public static final int HTTP_NOT_IMPLEMENTED = 501;
    public static final int HTTP_BAD_GATEWAY = 502;
    public static final int HTTP_SERVICE_UNAVAILABLE = 503;
    public static final int HTTP_GATEWAY_TIMEOUT = 504;
    public static final int HTTP_VERSION_NOT_SUPPORTED = 505;
    public static final int HTTP_VARIANT_ALSO_NEGOTIATES_EXPERIMENTAL = 506;                        // RFC2295
    public static final int HTTP_INSUFFICIENT_STORAGE = 507;                                        // RFC4918
    public static final int HTTP_LOOP_DETECTED = 508;                                               // RFC5842
    public static final int HTTP_NOT_EXTENDED = 510;                                                // RFC2774
    public static final int HTTP_NETWORK_AUTHENTICATION_REQUIRED = 511;

}
