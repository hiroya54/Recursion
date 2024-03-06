<?php

namespace Helpers;

class Settings{
    const ENV_PATH = ".env";

    public static function env(string $key) : string{
        $config = parse_ini_file(dirname(__FILE__,2) . "/" . self::ENV_PATH);

        if($config === false){
            throw new Exception('Could not read .env file.');
        }

        return $config[$key];
    }
}