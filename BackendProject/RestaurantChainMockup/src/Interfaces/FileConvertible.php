<?php


namespace Interfaces;

interface FileConvertible {
    public function toHTML() :string;
    public function toString() :string;
    public function toMarkdown() :string;
    public function toArray() :array;

}