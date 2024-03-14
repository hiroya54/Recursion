<?php

return [
    'User' => [
        'userId' => [
            'dataType' => 'INT',
            'constraints' => 'AUTO_INCREMENT',
            'primaryKey' => true,
            'nullable' => false,
        ],
        'username' => [
            'dataType' => 'VARCHAR(255)',
            'nullable' => false,
        ],
        'email' => [
            'dataType' => 'VARCHAR(255)',
            'nullable' => false,
        ],
        'password' => [
            'dataType' => 'VARCHAR(255)',
            'nullable' => false,
        ],
        'email_confirmed_at' => [
            'dataType' => 'VARCHAR(255)',
            'nullable' => true,
        ],
        'created_at' => [
            'dataType' => 'DATETIME',
            'nullable' => false,
        ],
        'updated_at' => [
            'dataType' => 'DATETIME',
            'nullable' => false,
        ]
    ],
    'Post' => [
        'postId' => [
            'dataType' => 'INT',
            'constraints' => 'AUTO_INCREMENT',
            'primaryKey' => true,
            'nullable' => false,
        ],
        'title' => [
            'dataType' => 'VARCHAR(255)',
            'nullable' => false,
        ],
        'content' => [
            'dataType' => 'TEXT',
            'nullable' => false,
        ],
        'created_at' => [
            'dataType' => 'DATETIME',
            'nullable' => false,
        ],
        'updated_at' => [
            'dataType' => 'DATETIME',
            'nullable' => false,
        ],
        'userId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'User',
                'referenceColumn' => 'userId',
                'onDelete' => 'CASCADE'
            ],
            'nullable' => false,
        ]
    ],
    'Comment' => [
        'commentId' => [
            'dataType' => 'INT',
            'constraints' => 'AUTO_INCREMENT',
            'primaryKey' => true,
            'nullable' => false,
        ],
        'commentText' => [
            'dataType' => 'VARCHAR(255)',
            'nullable' => false,
        ],
        'created_at' => [
            'dataType' => 'DATETIME',
            'nullable' => false,
        ],
        'updated_at' => [
            'dataType' => 'DATETIME',
            'nullable' => false,
        ],
        'userId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'User',
                'referenceColumn' => 'userId',
                'onDelete' => 'CASCADE'
            ],
            'nullable' => false,
        ],
        'postId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'Post',
                'referenceColumn' => 'postId',
                'onDelete' => 'CASCADE'
            ],
            'nullable' => false,
        ]
    ],
    'PostLike' => [
        'userId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'User',
                'referenceColumn' => 'userId',
                'onDelete' => 'CASCADE'
            ],
            'primaryKey' => true,
            'nullable' => false,
        ],
        'postId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'Post',
                'referenceColumn' => 'postId',
                'onDelete' => 'CASCADE'
            ],
            'primaryKey' => true,
            'nullable' => false,
        ]
    ],
    'CommentLike' => [
        'userId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'User',
                'referenceColumn' => 'userId',
                'onDelete' => 'CASCADE'
            ],
            'primaryKey' => true,
            'nullable' => false,
        ],
        'commentId' => [
            'dataType' => 'INT',
            'foreignKey' => [
                'referenceTable' => 'Comment',
                'referenceColumn' => 'commentId',
                'onDelete' => 'CASCADE'
            ],
            'primaryKey' => true,
            'nullable' => false,
        ]
    ]
];