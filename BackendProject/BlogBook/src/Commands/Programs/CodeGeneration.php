<?php

namespace Commands\Programs;

use Commands\AbstractCommand;

class CodeGeneration extends AbstractCommand{
    protected static ?string $alias = 'code-gen';
    protected static bool $requiredCommandValue = true;

    public static function getArguments(): array{
        return [];
    }

    public function execute() : int{
        $codeGenType = $this->getCommandValue();
        $this->log("Generating code for $codeGenType");
        return 0;
    }
}